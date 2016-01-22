package com.rac.recognum;

import java.util.HashMap;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.som.SOM;
import org.encog.neural.som.training.basic.BasicTrainSOM;
import org.encog.neural.som.training.basic.neighborhood.NeighborhoodSingle;

public class SimpleSOM {

    SOM network;
    double[][] trainigSet;
    HashMap<String, String> classification = new HashMap<String, String>();

    public void setTraining(double[][] trainingSet) {
        this.trainigSet = trainingSet;
        network = new SOM(trainingSet[0].length, 10);
        MLDataSet training = new BasicMLDataSet(trainingSet, null);
        // Create the neural network.

        network.reset();

        BasicTrainSOM train = new BasicTrainSOM(
                network,
                0.7,
                training,
                new NeighborhoodSingle());

        for (int i = 0; i < 20; i++) {
            train.iteration();
            double[][] d = network.getWeights().getArrayCopy();

            for (int c = 0; c < d[0].length; c++) {
                System.out.print(d[0][c] + " ,");
            }

            System.out.println();
            System.out.println(i + " Iteration: " + i + ", Error:" + train.getError());
        };
    }

    public void test() {
        MLData data1 = new BasicMLData(trainigSet[0]);
        MLData data2 = new BasicMLData(trainigSet[1]);
        MLData data3 = new BasicMLData(trainigSet[2]);
        MLData data4 = new BasicMLData(trainigSet[3]);
        MLData data5 = new BasicMLData(trainigSet[4]);
        MLData data6 = new BasicMLData(trainigSet[5]);
        MLData data7 = new BasicMLData(trainigSet[6]);
        MLData data8 = new BasicMLData(trainigSet[7]);
        MLData data9 = new BasicMLData(trainigSet[8]);

        int neuron = network.winner(data1);
        System.out.println("Pattern 1 winner: " + neuron + " neuron");
        classification.put(String.valueOf(neuron), "um");

        neuron = network.winner(data2);
        System.out.println("Pattern 2 winner: " + neuron + " neuron");
        classification.put(String.valueOf(neuron), "dois");

        neuron = network.winner(data3);
        System.out.println("Pattern 3 winner: " + neuron + " neuron");
        classification.put(String.valueOf(neuron), "tres");

        neuron = network.winner(data4);
        System.out.println("Pattern 4 winner: " + neuron + " neuron");
        classification.put(String.valueOf(neuron), "quatro");

        neuron = network.winner(data5);
        System.out.println("Pattern 5 winner: " + neuron + " neuron");
        classification.put(String.valueOf(neuron), "cinco");

        neuron = network.winner(data6);
        System.out.println("Pattern 6 winner: " + neuron + " neuron");
        classification.put(String.valueOf(neuron), "seis");

        neuron = network.winner(data7);
        System.out.println("Pattern 7 winner: " + neuron + " neuron");
        classification.put(String.valueOf(neuron), "sete");

        neuron = network.winner(data8);
        System.out.println("Pattern 8 winner: " + neuron + " neuron");
        classification.put(String.valueOf(neuron), "oito");

        neuron = network.winner(data9);
        System.out.println("Pattern 9 winner: " + neuron + " neuron");
        classification.put(String.valueOf(neuron), "nove");

        /**
         * System.out.println("Pattern 3 winner: " + network.winner(data3)+"
         * neuron"); System.out.println("Pattern 4 winner: " +
         * network.winner(data4)+" neuron"); System.out.println("Pattern 5
         * winner: " + network.winner(data5)+" neuron");
         * System.out.println("Pattern 6 winner: " + network.winner(data6)+"
         * neuron"); System.out.println("Pattern 7 winner: " +
         * network.winner(data7)+" neuron"); System.out.println("Pattern 8
         * winner: " + network.winner(data8)+" neuron");
         * System.out.println("Pattern 9 winner: " + network.winner(data9)+" neuron");
         */
        System.out.println("--------------------------------------------");
    }

    public int winner(double[] pattern) {
        MLData data = new BasicMLData(pattern);
        return network.winner(data);
    }

    public String getClassification(String neuron) {
        return classification.get(neuron);
    }
}
