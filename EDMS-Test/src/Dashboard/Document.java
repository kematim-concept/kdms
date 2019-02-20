package Dashboard;

public class Document {

    String Documentname;
    String DocummentDateofUpload;

    public String getDocummentDateofUpload() {
        return DocummentDateofUpload;
    }

    public void setDocummentDateofUpload(String docummentDateofUpload) {
        DocummentDateofUpload = docummentDateofUpload;
    }

    public Document(String documentname, String documentSize, String documentExtension, String dateofupload) {
        Documentname = documentname;
        DocumentSize = documentSize;
        DocumentExtension = documentExtension;
        DocummentDateofUpload = dateofupload;

    }

    String DocumentSize;

    public String getDocumentname() {
        return Documentname;
    }

    public void setDocumentname(String documentname) {
        Documentname = documentname;
    }

    public String getDocumentSize() {
        return DocumentSize;
    }

    public void setDocumentSize(String documentSize) {
        DocumentSize = documentSize;
    }

    public String getDocumentExtension() {
        return DocumentExtension;
    }

    public void setDocumentExtension(String documentExtension) {
        DocumentExtension = documentExtension;
    }

    String DocumentExtension;

}
