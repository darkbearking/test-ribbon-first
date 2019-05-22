package com.troila.lw.client;

import java.net.URI;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;

/**
 * 當前類需要與工程ribbon-server協同使用
 * 通過一系列的代碼，調用遠程服務（或者本地服務）
 * 服務端或服務提供端利用ribbon的負載均衡的功能，將請求平均分佈在幾個相同的遠程服務提供者的服務器上
 * 
 * 當前類的主要作用就是模擬調用http的請求。
 * @author liwei
 *
 */
public class TestRibbon {

	public static void main(String[] args) throws Exception{
		//下面的樣例寫法，可以參照github上關於ribbon的wiki內容
		//https://github.com/Netflix/ribbon/wiki/Getting-Started
		//本例其實就是參照了ribbon的wiki的Getting Started寫的。
		//我們還可以參照wiki的Working with load balancers，找到“Components of load balancer”下面的各種負載均衡的自定義規則類
		//也可以自己編寫自己的負載均衡規則類。
		
		//setProperties方法的第一個參數需要包含：客戶端名稱.命名空間.屬性 這三部分
		//第二個參數是屬性值（對應第一個參數的最後一部分內容）（可以是ip或者域名）
		//然後HttpRequest.newBuilder().uri這裡是請求的真實路徑，需要與上面的ip或地址綜合使用
		ConfigurationManager.getConfigInstance().setProperty("my-client.ribbon.listOfServers",
				"localhost:8080,localhost:8081");
		
		//下面這行要搭配上面一行使用。其含義為根據自定義的負載均衡規則類(NFLoadBalancerRuleClassName)實現負載均衡
		//（注意，規則在類中，比如ip或域名都有哪些，如何分發請求等）來實現負載均衡效果
		//但是，上面的那一行可以自己單獨出現而不報錯。
		ConfigurationManager.getConfigInstance().setProperty("my-client.ribbon.NFLoadBalancerRuleClassName",
				MyRule.class.getName());

		RestClient client = (RestClient)ClientFactory.getNamedClient("my-client");
		//官方示例代碼中，這裡使用的是HttpClientRequest和HTTPClientResponse。不知為什麼在這裡一直報錯。
		//因此換成去掉Client的request和response了
		HttpRequest request = HttpRequest.newBuilder().uri(new URI("/person")).build();
		for(int i = 0 ;i < 10 ; i++) {
			HttpResponse response =   client.executeWithLoadBalancer(request);
			String json = response.getEntity(String.class);
			System.out.println(json);
		}
	}

}
