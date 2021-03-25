package inventory.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.util.Date;
import java.util.Locale;

@Entity
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Integer masterId;

	@Column
	private String masterTitle;

	@Column
	@JsonFormat(pattern="yyyy/MM/dd hh:mm:ss")
	private Date createDate;

	@Lob
	@Column
	private String functionName;

	@Column
	private String userName;

	@Lob
	@Column
	private String input;

	@Lob
	@Column
	private String output;

	public Log(){
		super();
	}
	public Log(Integer inventoryMasterId, String masterTitle, String functionName, Object input, Object output, String userName) {
		super();
		this.masterId = inventoryMasterId;
		this.masterTitle = masterTitle;
		this.createDate = new Date();
		if(functionName!=null)
			this.functionName = functionName.trim().toUpperCase();

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.input = objectMapper.writeValueAsString(input);
		}catch(Exception ex){
			ex.printStackTrace();
		}

		try {
			this.output = objectMapper.writeValueAsString(output);
		}catch(Exception ex){
			ex.printStackTrace();
		}

		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}


	public Integer getMasterId() {
		return masterId;
	}

	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	public String getMasterTitle() {
		return masterTitle;
	}

	public void setMasterTitle(String masterTitle) {
		this.masterTitle = masterTitle;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}