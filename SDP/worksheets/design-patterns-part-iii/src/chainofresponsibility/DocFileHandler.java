package chainofresponsibility;

public class DocFileHandler implements Handler {
    String handlerName = null;
    Handler forwardTo = null;

    public DocFileHandler(String doc_handler) {
        this.handlerName = doc_handler;

    }

    @Override
    public void setHandler(Handler handler) {
        this.forwardTo = handler;
    }

    @Override
    public void process(File file) {
        if (file.getFileType() == "doc") {
            System.out.println("Process and saving text file... by " + this.getHandlerName());
        } else if (this.forwardTo != null) {
            System.out.println(this.getHandlerName() + " forwards request to " + this.forwardTo.getHandlerName());
            this.forwardTo.process(file);
        } else {
            System.out.println("File not supported");
        }
    }

    @Override
    public String getHandlerName() {
        return this.handlerName;
    }
}
