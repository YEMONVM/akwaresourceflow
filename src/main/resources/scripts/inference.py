import pandas as pd
import xgboost as xgb
import joblib
import click
import holidays
from datetime import timedelta
import numpy as np
import random
import warnings
import os

# Set seeds for reproducibility
np.random.seed(42)
random.seed(42)

# Ignore warnings
warnings.simplefilter(action='ignore', category=FutureWarning)

@click.command()
@click.option('--date', prompt='Enter the end date for prediction (MM-DD-YYYY)', type=str)
def predict_sales(date):
    """
    Predicts sales counts for each day from the last date in the dataset up to
    a given target date using a trained XGBoost model. Updates the inventory based on actual sales and dynamically schedules deliveries.

    Parameters:
    - date (str): The target date up to which the sales predictions are to be made, in the format "MM-DD-YYYY".

    Returns:
    None. Prints the predicted inventory levels and sales counts for each date, and estimated delivery amounts on delivery days.
    """
    # Define the paths to the trained model files and dataset
    current_dir = os.path.dirname(os.path.abspath(__file__))
    model_sales_path = os.path.join(current_dir, 'xgb_model_sales.pkl')
    label_encoder_path = os.path.join(current_dir, 'label_encoder.pkl')
    dataset_path = os.path.join(current_dir, 'dataset.csv')

    # Load the trained model and label encoder
    xgb_model_sales = joblib.load(model_sales_path)
    le = joblib.load(label_encoder_path)

    # Load the previous dataset
    dataset = pd.read_csv(dataset_path)
    dataset['Date'] = pd.to_datetime(dataset['Date'])

    # Ensure the dataset is sorted by date
    dataset = dataset.sort_values(by='Date')

    # Last date in the dataset
    last_date = dataset['Date'].iloc[-1]
    current_inventory = dataset['Inventory'].iloc[-1]
    target_date = pd.to_datetime(date, format='%m-%d-%Y')

    predictions = []

    # Prediction Loop:
    while last_date < target_date:
        # Preprocess the data
        last_date += timedelta(days=1)
        input_data = pd.DataFrame({'Date': [last_date]})
        input_data['weekday'] = input_data['Date'].dt.day_name()
        input_data['holiday'] = input_data['Date'].apply(lambda x: x in holidays.US()).astype(int)
        input_data['weekday'] = le.transform(input_data['weekday'])

        # Generate lag features from the previous dataset
        for i in range(1, 8):
            input_data[f'lag_{i}'] = dataset['Sale Count'].iloc[-i]

        # Predict using the loaded model
        prediction_sales = round(xgb_model_sales.predict(input_data.drop('Date', axis=1))[0])

        # Update real inventory based on previous day's predicted sales
        current_inventory = max(current_inventory - dataset['Sale Count'].iloc[-1], 0)

        delivery_amount = 0
        if last_date.weekday() in [1, 4]:  # Tuesday or Friday
            if last_date.weekday() == 1:  # Tuesday
                days_to_cover = 3  # Tuesday to Thursday
            else:  # Friday
                days_to_cover = 4  # Friday to Monday

            total_sales_to_cover = 0
            for i in range(1, days_to_cover + 1):
                future_date = last_date + timedelta(days=i)
                future_data = pd.DataFrame({'Date': [future_date]})
                future_data['weekday'] = future_data['Date'].dt.day_name()
                future_data['holiday'] = future_data['Date'].apply(lambda x: x in holidays.US()).astype(int)
                future_data['weekday'] = le.transform(future_data['weekday'])
                for j in range(1, 8):
                    future_data[f'lag_{j}'] = dataset['Sale Count'].iloc[-j]
                future_sales = round(xgb_model_sales.predict(future_data.drop('Date', axis=1))[0])
                total_sales_to_cover += future_sales

            total_sales_to_cover *= 1.15  # Adding 15% buffer to the total sales
            if current_inventory < total_sales_to_cover:
                delivery_amount = total_sales_to_cover - current_inventory
                current_inventory += delivery_amount

        predictions.append({
            'Date': last_date.strftime('%Y-%m-%d'),
            'Real Inventory': current_inventory,
            'Predicted Sales': prediction_sales,
            'Estimated Delivery Amount': delivery_amount
        })

        # Append the predictions to the dataset for subsequent lag features
        new_row = pd.DataFrame({
            'Date': [last_date],
            'Inventory': [current_inventory],
            'Sale Count': [prediction_sales]
        })
        dataset = pd.concat([dataset, new_row], ignore_index=True)

    # Convert delivery amounts and inventory values to integers
    predictions_df = pd.DataFrame(predictions).astype({
        'Real Inventory': 'int',
        'Predicted Sales': 'int',
        'Estimated Delivery Amount': 'int'
    })

    # Display the predictions
    print(predictions_df)

if __name__ == "__main__":
    predict_sales()
