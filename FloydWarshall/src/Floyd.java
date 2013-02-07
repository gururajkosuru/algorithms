




public class Floyd {
	int source;
	int[][][] cost;
	int[][] path;
	node[] nodeList;
	edge[] edgeList;
	Floyd(int source){
		this.source = source;
	}




	private void process(int[][] edgelistGiven, int size,int source) {

		cost = new int[size][size][size];
		path = new int[size][size];
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
			//nodeList[vertex2].edges.add(i);
			nodeList[vertex1].edges.add(i);

		}


		//Initialization
		for(int k=0;k<1;k++)
			for(int i=0;i<size;i++)
				for(int j=0;j<size;j++){
					if(i==j)
						cost[i][j][k] = 0;
					else{
						cost[i][j][k] = Integer.MAX_VALUE;
						for(int e:nodeList[i].edges){
							int otherVert = edgeList[e].vertices[0] == nodeList[i].number?edgeList[e].vertices[1]:edgeList[e].vertices[0];
							if(otherVert==nodeList[j].number){
								cost[i][j][k] = edgeList[e].weight;

							}
						}

					}

				}
		
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				path[i][j] = -1;

	}

	private void warshall(int size) {
		for(int k=1;k<size;k++)
			for(int i=0;i<size;i++)
				for(int j=0;j<size;j++){
					cost[i][j][k] = cost[i][j][k-1];
					//path[i][j] = k-1;
					if(cost[i][j][k] > cost[i][k][k-1]+cost[k][j][k-1] && cost[i][k][k-1]<Integer.MAX_VALUE && cost[k][j][k-1]<Integer.MAX_VALUE){
						cost[i][j][k]= cost[i][k][k-1]+cost[k][j][k-1];
						path[i][j] = k;
					}
				}

	}

	int min(int a,int b){
		return a< b? a:b;
	}

	public static void main(String farts[]){
		int source = 0,size=6;
		//int[][] edgeList = {{0,1,-10},{1,2,-1},{2,0,-4},{0,2,-20},{2,3,10},{3,4,12},{2,4,120},{4,5,20},{2,5,1}};
		int[][] edgeList = {{0,1,10},{1,2,1},{0,2,20},{2,3,10},{3,4,12},{2,4,120},{4,5,20},{2,5,1}};
		Floyd floyd = new Floyd(source);
		floyd.process(edgeList,size,source);
		floyd.warshall(size);

		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++)
				System.out.print(" "+floyd.cost[i][j][size-1]);
			System.out.println();
		}


		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++)
				System.out.print(" "+floyd.path[i][j]);
			System.out.println();
		}


	}





}