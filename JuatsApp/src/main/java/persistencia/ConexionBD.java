package persistencia;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;


public class ConexionBD 
{
    private final MongoDatabase DATABASE;
    
    public ConexionBD()
    {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojoCodecRegistry);

        ConnectionString cadenaConexion = new ConnectionString("mongodb://localhost/27017");

        MongoClientSettings clientsSettings = MongoClientSettings.builder().applyConnectionString(cadenaConexion).codecRegistry(codecRegistry).build();

        MongoClient dbServer = MongoClients.create(clientsSettings);

        this.DATABASE = dbServer.getDatabase("juatsappdb");
    }

    
    public MongoDatabase getBaseDatos()
    {
        return DATABASE;
    }
}
