package com.cyberSpeed.scratchGame;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Config {
    private int columns;
    private int rows;
    private Map<String, Symbol> symbols;
    private Probabilities probabilities;
    private Map<String, WinCombination> winCombinations;

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(Map<String, Symbol> symbols) {
        this.symbols = symbols;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }

    public Map<String, WinCombination> getWinCombinations() {
        return winCombinations;
    }

    public void setWinCombinations(Map<String, WinCombination> winCombinations) {
        this.winCombinations = winCombinations;
    }

    public static class Probabilities {
        private List<Probability> standardSymbols;
        private Probability bonusSymbols;

        // Getters and setters

        public List<Probability> getStandardSymbols() {
            return standardSymbols;
        }

        public void setStandardSymbols(List<Probability> standardSymbols) {
            this.standardSymbols = standardSymbols;
        }

        public Probability getBonusSymbols() {
            return bonusSymbols;
        }

        public void setBonusSymbols(Probability bonusSymbols) {
            this.bonusSymbols = bonusSymbols;
        }
    }
    public class ImpactDeserializer extends JsonDeserializer<Integer> {
        @Override
        public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String value = p.getText();
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IOException("Invalid impact value: " + value);
            }
        }
}
