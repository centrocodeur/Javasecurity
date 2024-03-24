package com.example.demoahachage;

import org.apache.commons.codec.digest.DigestUtils;

public class ValeurApresHashage {
	public String hash(String mdpAHasher){
		return DigestUtils.sha256Hex(mdpAHasher);
	}


}
