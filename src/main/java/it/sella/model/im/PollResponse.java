package it.sella.model.im;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PollResponse {

	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("errors")
	@Expose
	private List<Error> errors = null;
	@SerializedName("results")
	@Expose
	private List<Result> results = null;
	@SerializedName("errorMessageCode")
	@Expose
	private Object errorMessageCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public Object getErrorMessageCode() {
		return errorMessageCode;
	}

	public void setErrorMessageCode(Object errorMessageCode) {
		this.errorMessageCode = errorMessageCode;
	}

	@Override
	public String toString() {
		return "PollResponse [status=" + status + ", errors=" + errors + ", results=" + results + ", errorMessageCode="
				+ errorMessageCode + "]";
	}
	

}
