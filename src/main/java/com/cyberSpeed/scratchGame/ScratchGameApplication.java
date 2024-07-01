package com.cyberSpeed.scratchGame;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.*;

public class ScratchGameApplication {
	private Config config;

	public ScratchGameApplication(Config config) {
		this.config = config;
	}

	public char[][] generateRandomMatrix() {
		int rows = config.getRows();
		int columns = config.getColumns();
		char[][] matrix = new char[rows][columns];

		Random random = new Random();

		for (Probability prob : config.getProbabilities().getStandardSymbols()) {
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					matrix[i][j] = getRandomSymbol(prob.getSymbols(), random);
				}
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (matrix[i][j] == '\0') {
					matrix[i][j] = getRandomBonusSymbol(config.getProbabilities().getBonusSymbols().getSymbols(), random);
				}
			}
		}
		return matrix;
	}

	private char getRandomSymbol(Map<String, Integer> symbolProbabilities, Random random) {
		int totalWeight = symbolProbabilities.values().stream().mapToInt(Integer::intValue).sum();
		int randomIndex = random.nextInt(totalWeight);
		int currentWeight = 0;
		for (Map.Entry<String, Integer> entry : symbolProbabilities.entrySet()) {
			currentWeight += entry.getValue();
			if (randomIndex < currentWeight) {
				return entry.getKey().charAt(0);
			}
		}
		return ' ';
	}

	private char getRandomBonusSymbol(Map<String, Integer> bonusProbabilities, Random random) {
		return getRandomSymbol(bonusProbabilities, random);
	}

	public double calculateWinnings(char[][] matrix, double betAmount) {
		double totalReward = 0.0;
		Map<String, Integer> symbolCounts = new HashMap<>();

		for (char[] row : matrix) {
			for (char symbol : row) {
				symbolCounts.put(String.valueOf(symbol), symbolCounts.getOrDefault(String.valueOf(symbol), 0) + 1);
			}
		}

		for (WinCombination combination : config.getWinCombinations().values()) {
			if ("same_symbols".equals(combination.getWhen())) {
				for (Map.Entry<String, Integer> entry : symbolCounts.entrySet()) {
					if (entry.getValue() >= combination.getCount()) {
						totalReward += betAmount * config.getSymbols().get(entry.getKey()).getRewardMultiplier() * combination.getRewardMultiplier();
					}
				}
			} else if ("linear_symbols".equals(combination.getWhen())) {
				for (List<String> coveredArea : combination.getCoveredAreas()) {
					boolean match = true;
					String symbol = String.valueOf(matrix[Character.getNumericValue(coveredArea.get(0).charAt(0))][Character.getNumericValue(coveredArea.get(0).charAt(2))]);
					for (String pos : coveredArea) {
						int row = Character.getNumericValue(pos.charAt(0));
						int col = Character.getNumericValue(pos.charAt(2));
						if (!String.valueOf(matrix[row][col]).equals(symbol)) {
							match = false;
							break;
						}
					}
					if (match) {
						totalReward += betAmount * config.getSymbols().get(symbol).getRewardMultiplier() * combination.getRewardMultiplier();
					}
				}
			}
		}

		return totalReward;
	}

	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Config config = mapper.readValue(new File("config.json"), Config.class);
//		ScratchGameApplication game = new ScratchGameApplication(config);

//		char[][] matrix = game.generateRandomMatrix();
//		System.out.println("Generated Matrix:");
//		for (char[] row : matrix) {
//			System.out.println(Arrays.toString(row));
//		}

//		double betAmount = 100.0;
//		double winnings = game.calculateWinnings(matrix, betAmount);
//		System.out.println("Winnings: " + winnings);

		System.out.println("osman");
	}
}
