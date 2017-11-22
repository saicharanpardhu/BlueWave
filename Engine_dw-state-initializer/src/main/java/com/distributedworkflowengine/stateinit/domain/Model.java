package com.distributedworkflowengine.stateinit.domain;

import org.springframework.stereotype.Component;

@Component
public class Model {

		private String jobId;
		private String stage;
	    private String type;
	    private String[] input;
	    private String[] output;
	    public Model(String jobId, String stage, String type, String[] input, String[] output, int errcode,
				String stderr) {
			super();
			this.jobId = jobId;
			this.stage = stage;
			this.type = type;
			this.input = input;
			this.output = output;
			this.errcode = errcode;
			this.stderr = stderr;
		}
		public Model(String jobId) {
			super();
			this.jobId = jobId;
		}
		public Model() {
			super();
		}
		public String getJobId() {
			return jobId;
		}
		public void setJobId(String jobId) {
			this.jobId = jobId;
		}
		public String getStage() {
			return stage;
		}
		public void setStage(String stage) {
			this.stage = stage;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String[] getInput() {
			return input;
		}
		public void setInput(String[] input) {
			this.input = input;
		}
		public String[] getOutput() {
			return output;
		}
		public void setOutput(String[] output) {
			this.output = output;
		}
		public int getErrcode() {
			return errcode;
		}
		public void setErrcode(int errcode) {
			this.errcode = errcode;
		}
		public String getStderr() {
			return stderr;
		}
		public void setStderr(String stderr) {
			this.stderr = stderr;
		}
		private int errcode;
	    private String stderr;
}
