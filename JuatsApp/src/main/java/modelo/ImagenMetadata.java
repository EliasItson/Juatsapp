package modelo;

import org.bson.types.ObjectId;


public class ImagenMetadata 
{
    private ObjectId id;
    private String filename;
    private String contentType;
    private long length;

    public ImagenMetadata(ObjectId id, String filename, String contentType, long length) {
        this.id = id;
        this.filename = filename;
        this.contentType = contentType;
        this.length = length;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
    
    
}
