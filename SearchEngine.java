import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    String str = "String:\n";
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return str;
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                str += parameters[1]+"\n";
                return String.format("String added<3");
            }
            if (url.getPath().contains("/search")){
                String newstr = "Here are your search results: ";
                String[] parameters2 = url.getQuery().split("=");
                String[] filter = str.split("\n");
                for (int i=0; i<filter.length; i++){
                    if (filter[i].contains(parameters2[1])){
                        newstr += filter[i]+"\n";
                    }
                }
                return newstr;
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
