package com.akwaresourceflow.controllers;

import org.springframework.web.bind.annotation.*;
import java.io.*;

@RestController
@RequestMapping("/api")
public class MLController {

    @PostMapping("/train")
    public String trainModel() {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "src/main/resources/scripts/training.py");
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitCode = p.waitFor();
            if (exitCode == 0) {
                return "Training completed successfully:\n" + output.toString();
            } else {
                return "Training failed with exit code " + exitCode + ":\n" + output.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during training: " + e.getMessage();
        }
    }

    @PostMapping("/predict")
    public String predictSales(@RequestParam String date) {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "src/main/resources/scripts/inference.py", "--date", date);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                output.append(line).append("\n");
            }
            int exitCode = p.waitFor();
            if (exitCode == 0) {
                return "Prediction results:\n" + output.toString();
            } else {
                return "Prediction failed with exit code " + exitCode + ":\n" + output.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during prediction: " + e.getMessage();
        }
    }
}
