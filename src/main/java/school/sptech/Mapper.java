package school.sptech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public List<Stock> map(InputStream inputStream) throws IOException {
        List<Stock> stocks = new ArrayList<>();
        String linha;
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            // Pular o cabeçalho
            br.readLine();

            // Ler linha por linha
            while ((linha = br.readLine()) != null) {
                Stock stock = getStock(linha, separador);

                // Adicionar à lista
                stocks.add(stock);
            }
        }

        return stocks;
    }

    private static Stock getStock(String linha, String separador) {
        String[] dados = linha.split(separador);

        // Mapear os dados para o objeto Stock
        Stock stock = new Stock();
        stock.setIdDados(Integer.parseInt(dados[0]));
        stock.setDataHora(dados[1]);
        stock.setPercCPU(Double.parseDouble(dados[2]));
        stock.setTempoInativo(Double.parseDouble(dados[3]));
        stock.setPercRAM(Double.parseDouble(dados[4]));
        stock.setPercDisc(Double.parseDouble(dados[5]));
        stock.setUsedDisc(Long.parseLong(dados[6]));
        stock.setFkNotebook(Integer.parseInt(dados[7]));
        return stock;
    }
}
