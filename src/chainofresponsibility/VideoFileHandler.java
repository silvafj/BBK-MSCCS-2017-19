package chainofresponsibility;

public class VideoFileHandler implements Handler {
    String handlerName = null;
    Handler forwardTo = null;

    public VideoFileHandler(String video_handler) {
        this.handlerName = video_handler;

    }

    @Override
    public void setHandler(Handler handler) {
        this.forwardTo = handler;
    }

    @Override
    public void process(File file) {
        if (file.getFileType() == "video") {
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
