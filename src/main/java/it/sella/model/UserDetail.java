
package it.sella.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "first_name", "last_name", "profile_pic", "id" })
public class UserDetail {

	@Override
	public String toString() {
		return "UserDetail [firstName=" + firstName + ", lastName=" + lastName + ", profilePic=" + profilePic + ", id="
				+ id + ", additionalProperties=" + additionalProperties + "]";
	}

	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("profile_pic")
	private String profilePic;
	@JsonProperty("id")
	private String id;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("first_name")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("last_name")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("profile_pic")
	public String getProfilePic() {
		return profilePic;
	}

	@JsonProperty("profile_pic")
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}