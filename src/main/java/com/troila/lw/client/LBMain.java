package com.troila.lw.client;

import java.util.ArrayList;
import java.util.List;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 測試ribbon自帶的的負載均衡效果
 * @author liwei
 *
 */
public class LBMain {

	public static void main(String[] args) {
		//創建一個負載均衡器的對象
		ILoadBalancer lb = new BaseLoadBalancer();
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
