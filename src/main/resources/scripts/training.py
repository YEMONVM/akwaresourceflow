import pandas as pd
import xgboost as xgb
from sklearn.preprocessing import LabelEncoder
from sklearn.model_selection import train_test_split
import joblib
import numpy as np
import random
import warnings
import holidays
import os

# Set seeds for reproducibility
np.random.seed(42)
random.seed(42)

# Ignore warnings
warnings.simplefilter(action='ignore', category=FutureWarning)

# Define the paths for dataset and model files
current_dir = os.path.dirname(os.path.abspath(__file__))
dataset_path = os.path.join(current_dir, 'dataset.csv')
model_sales_path = os.path.join(current_dir, 'xgb_model_sales.pkl')
label_encoder_path = os.path.join(current_dir, 'label_encoder.pkl')

# Load the dataset
dataset = pd.read_csv(dataset_path)

# Preprocess the data
dataset['Date'] = pd.to_datetime(dataset['Date'])
dataset['weekday'] = dataset['Date'].dt.day_name()
dataset['holiday'] = dataset['Date'].apply(lambda x: x in holidays.US()).astype(int)

# Create lag features to capture historical sales values
for i in range(1, 8):
    dataset[f'lag_{i}'] = dataset['Sale Count'].shift(i)
dataset.dropna(inplace=True)

# Convert weekday names to numerical labels for model training
le = LabelEncoder()
dataset['weekday'] = le.fit_transform(dataset['weekday'])

# Split the data into features (X) and target (y_sales)
X = dataset.drop(['Date', 'Product ID', 'Inventory', 'Sale Count'], axis=1)
y_sales = dataset['Sale Count']

# Split the dataset into training and test sets without shuffling (keeping the time series order)
X_train, X_test, y_train_sales, y_test_sales = train_test_split(X, y_sales, test_size=0.2, shuffle=False)

# Train the XGBoost model for sales prediction
xgb_model_sales = xgb.XGBRegressor()
xgb_model_sales.fit(X_train, y_train_sales)

# Save the trained model and the label encoder
joblib.dump(xgb_model_sales, model_sales_path)
joblib.dump(le, label_encoder_path)
