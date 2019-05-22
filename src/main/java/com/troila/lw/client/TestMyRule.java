package com.troila.lw.client;

import java.util.ArrayList;
import java.util.List;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 測試自定義的規則類及其效果
 * @author liwei
 *
 */
public class TestMyRule {

	public static void main(String[] args) {
		//創建一個負載均衡器的對象
		BaseLoadBalancer lb = new BaseLoadBalancer();
		MyRule rule = new MyRule();
		rule.setLoadBalancer(lb);
		lb.setRule(rule);
		
		List<Server> servers = new ArrayList<Server>();
		servers.add(new Server("localhost",8080));
		servers.add(new Server("localhost",8081));
		
		lb.addServers(servers);
		for(int i = 0 ; i < 10 ; i++) {
			//括號里是空，說明使用的是默認的規則
			Server s = lb.chooseServer(null);	
			System.out.println(s);
		}
	}

}
