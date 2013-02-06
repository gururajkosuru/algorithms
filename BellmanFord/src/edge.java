	class edge{
		public edge(int vertex1, int vertex2, int weight2) {
			this.vertices[0] = vertex1;
			this.vertices[1] = vertex2;
			this.weight = weight2;
		}
		int weight;
		int[] vertices = new int[2];
	}