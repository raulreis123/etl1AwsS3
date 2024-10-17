package school.sptech;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class Main implements RequestHandler<S3Event, String> {

    // Criação do cliente S3 para acessar os buckets
    private final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

    // Bucket de destino para o CSV gerado
    private static final String DESTINATION_BUCKET = "bucket-trusted-tag-tech";

    @Override
    public String handleRequest(S3Event s3Event, Context context) {

        // Extraímos o nome do bucket de origem e a chave do arquivo CSV
        String sourceBucket = s3Event.getRecords().getFirst().getS3().getBucket().getName();
        String sourceKey = s3Event.getRecords().getFirst().getS3().getObject().getKey();


        try {
            // Leitura do arquivo CSV do bucket de origem
            InputStream s3InputStream = s3Client.getObject(sourceBucket, sourceKey).getObjectContent();
            Mapper mapper = new Mapper();
            // Conversão do CSV para uma lista de objetos Stock usando o Mapper
            List<Stock> stocks = mapper.map(s3InputStream);
            // Geração do arquivo CSV a partir da lista de Stock usando o CsvWriter
            CsvWriter csvWriter = new CsvWriter();


            // Verificando existência de arquivo no bucket de destino
            boolean s3ObjExist = s3Client.doesObjectExist(DESTINATION_BUCKET, "allData.csv");
            if(s3ObjExist){
                InputStream s3InputStreamNew = s3Client.getObject(DESTINATION_BUCKET, "allData.csv").getObjectContent();
                List<Stock> stocks1 = mapper.map(s3InputStreamNew);
                s3Client.deleteObject(DESTINATION_BUCKET, "allData.csv");

                ByteArrayOutputStream csvOutputStream = csvWriter.writeCsv(stocks, stocks1);
                InputStream csvInputStream = new ByteArrayInputStream(csvOutputStream.toByteArray());

                s3Client.putObject(DESTINATION_BUCKET, "allData.csv", csvInputStream, null);
            } else{
                ByteArrayOutputStream csvOutputStream = csvWriter.writeCsv(stocks);

                // Converte o ByteArrayOutputStream para InputStream para enviar ao bucket de destino
                InputStream csvInputStream = new ByteArrayInputStream(csvOutputStream.toByteArray());

                // Envio do CSV para o bucket de destino
                s3Client.putObject(DESTINATION_BUCKET, "allData.csv", csvInputStream, null);
            }
            return "Sucesso no processamento";
        } catch (Exception e) {
            // Tratamento de erros e log do contexto em caso de exceção
            context.getLogger().log("Erro: " + e.getMessage());
            return "Erro no processamento";
        }
    }
}