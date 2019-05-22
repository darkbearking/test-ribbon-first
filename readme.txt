對應楊恩雄視頻中的ribbon-server和ribbon-client兩個工程。
服務端在本工程的server包中
客戶端在本工程的client包中，并調用server包中的服務
本工程包含了ribbon的相關jar包。
其作用是通過調用ribbon的一些功能類，實現負載均衡的方式
重點：ribbon出現在服務調用端，而非最終服務提供端

當一個服務以分佈式的方式提供出來的時候，我們只需要關心服務實例
而不必關心服務的後端有哪些具體服務。
通過集成ribbon，可以只需要調用服務實例名，進而由ribbon協助我們做到對實例訪問的負載均衡。

同時當前工程中，還包含了自定義的負載均衡規則類以及ribbon的幾種負載均衡機制
這種自定義類實現了IRule接口

RoundRobinRule 				//平均輪詢規則
AvailabilityFilteringRule 	//服務器三次連接失敗的被忽略規則，並發數過高的服務器也會被忽略，相關配置間Git
WeightedResponseTimeRule 	//每個服務器的相應權重值規則
ZoneAvoidanceRule 			//按照區域為可用服務器分類，然後按照不同的區域來處理負載的規則
BestAvailableRule 			//忽略那些以備短路，而且並發較低的服務器規則
RandomRule 					//隨機選用服務器規則
RetryRule 					//重試規則，有時候和平均輪詢規則配合使用
	
NFLoadBalancePingClassName		//實現通過ping測試服務器是否存活
NFLoadBalanceClassName			//實現指定自定義負載均衡器
NIWSServerListClassName			//實現指定服務器列表
NIWSServerListFilterClassName	//實現服務器的攔截