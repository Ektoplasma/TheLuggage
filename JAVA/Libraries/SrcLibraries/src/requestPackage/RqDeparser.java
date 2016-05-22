package requestPackage;

public class RqDeparser {
	
	public int verifRequest(String request){

		String[] requestParser;

		requestParser=request.split(":");

		if(requestParser[0].compareTo("0")==0){
			return 0;
		}
		else if(requestParser[0].compareTo("1")==0){
			return 1;
		}

		return 0;

	}

	public String requestName (String request){
		String[] requestName;

		requestName=request.split(":");

		return requestName[1];

	}

	public String[] deparseArgs (String request){
		String[] requestArgs;
		String[] argsToReturn;

		requestArgs=request.split(":");

		argsToReturn=requestArgs[2].split(";");

		return argsToReturn;

	}

}
