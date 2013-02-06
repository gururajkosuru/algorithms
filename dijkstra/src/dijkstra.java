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

	private void process(int[][] edgelistGiven, int size,int source) {

		vertices = new cost[size];
		nodeList = new node[size];
		edgeList = new edge[edgelistGiven.length];
		for(int i=0;i<size;i++)
			nodeList[i] = new node(i);

		for(int i=0;i<edgelistGiven.length;i++){
			int vertex1 = edgelistGiven[i][0];
			int vertex2 = edgelistGiven[i][1];
			int weight =  edgelistGiven[i][2];
			edge e = new edge(vertex1,vertex2,weight);
			edgeList[i] = e;
			nodeList[vertex1].edges.add(i);
			//noeList[vertex2].edges.add(e);
			


		}

		for(int i=0;i<size;i++)
			vertices[i] = new cost(i, Integer.MAX_VALUE);
		vertices[0].dist = 0;
	}

	public static void main(String farts[]){

		int source = 0,size=6;
		int[][] edgeList = {{0,1,10},{1,2,1},{0,2,20},{2,3,10},{3,4,12},{2,4,120},{4,5,20},{2,5,1}};	
		dijkstra dij = new dijkstra(source);
		dij.process(edgeList,size,source);
		dij.getDijkstra();
		for(int i=0;i<size;i++)
			System.out.print(dij.vertices[i].dist+" ");

	}
}
