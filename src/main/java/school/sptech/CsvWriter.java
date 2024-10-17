package school.sptech;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CsvWriter {
    // Geração de arquivo caso não exista no bucket trusted
    public ByteArrayOutputStream writeCsv(List<Stock> stocks) throws IOException {
        // Criar um CSV em memória utilizando ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("idDados", "dataHora", "percCPU", "tempoInvativo", "percRAM", "percDisc", "usedDisc", "fkNotebook"));

        // Processar e escrever cada objeto no CSV em linha
        for (Stock stock : stocks) {
            csvPrinter.printRecord(
                    stock.getIdDados(),
                    stock.getDataHora(),
                    stock.getPercCPU(),
                    stock.getTempoInativo(),
                    stock.getPercRAM(),
                    stock.getPercDisc(),
                    stock.getUsedDisc(),
                    stock.getFkNotebook()
            );
        }

        // Fechar o CSV para garantir que todos os dados sejam escritos
        csvPrinter.flush();
        writer.close();

        // Retornar o ByteArrayOutputStream que contém o CSV gerado
        return outputStream;
    }


    // Sobrecarga de méto-do para geração de arquivo caso exista no bucket trusted
    public ByteArrayOutputStream writeCsv(List<Stock> stocks, List<Stock> stocks1) throws IOException {
        // Criar um CSV em memória utilizando ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("idDados", "dataHora", "percCPU", "tempoInvativo", "percRAM", "percDisc", "usedDisc", "fkNotebook"));
        // Pegando último id gerado para mesclagem
        Stock lastObject = stocks.get(stocks.size() - 1);
        Integer lastId = lastObject.getIdDados();

        // Processar e escrever cada objeto no CSV em linha
        for (Stock stock : stocks) {
            csvPrinter.printRecord(
                    stock.getIdDados(),
                    stock.getDataHora(),
                    stock.getPercCPU(),
                    stock.getTempoInativo(),
                    stock.getPercRAM(),
                    stock.getPercDisc(),
                    stock.getUsedDisc(),
                    stock.getFkNotebook()
            );
        }

        // Processar e escrever objetos de stocks1, incrementando o idDados
        for (Stock stock : stocks1) {
            csvPrinter.printRecord(
                    stock.getIdDados() + lastId,
                    stock.getDataHora(),
                    stock.getPercCPU(),
                    stock.getTempoInativo(),
                    stock.getPercRAM(),
                    stock.getPercDisc(),
                    stock.getUsedDisc(),
                    stock.getFkNotebook()
            );
        }


        // Fechar o CSV para garantir que todos os dados sejam escritos
        csvPrinter.flush();
        writer.close();

        // Retornar o ByteArrayOutputStream que contém o CSV gerado
        return outputStream;
    }
}
