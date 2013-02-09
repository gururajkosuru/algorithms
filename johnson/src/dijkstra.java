import java.util.ArrayList;
import java.util.Arrays;




public class dijkstra {
	int source;
	cost[] vertices;
	node[] nodeList;
	edge[] edgeList;
	int flag = 0;
	dijkstra(int source){
		this.source = source;
	}



	class cost implements Comparable<cost> {
		int vertexNum;
		int dist;
		public cost(int i, int maxValue) {
			this.vertexNum = i;
			this.dist = maxValue;
		}
		@Override
		public int compareTo(cost o) {
			if(flag==1)
				return this.dist - o.dist;
			else
				return this.vertexNum - o.vertexNum;
		}

	}


	public void getDijkstra(){
		int processed = 0;

		while(processed<vertices.length){
			flag = 1;
			Arrays.sort(vertices);
			int currVertexNum = vertices[processed].vertexNum;
			flag = 0;
			Arrays.sort(vertices);
			for(int e:nodeList[currVertexNum].edges){
				int otherEnd = edgeList[e].vertices[0] == currVertexNum? edgeList[e].vertices[1]: edgeList[e].vertices[0];
				if(vertices[currVertexNum].dist!=Integer.MAX_VALUE &&vertices[currVertexNum].dist+edgeList[e].weight<vertices[otherEnd].dist)
					vertices[otherEnd].dist = vertices[currVertexNum].dist+edgeList[e].weight;
			}
			processed++;

		}


	}

	void process( edge[] edgeList,node[] nodeList, int size,int source) {

		vertices = new cost[size];
		this.nodeList = nodeList;
		this.edgeList = edgeList;
		

		for(int i=0;i<size;i++)
			vertices[i] = new cost(i, Integer.MAX_VALUE);
		vertices[source].dist = 0;
	}


}
