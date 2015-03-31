# Labeled Digraph ADT

class LabeledDigraph():
	#construct Labeled Digraph ADT
	def __init__(self):
		self.graph_dict = {}

	#returns true iff graph is empty
	def is_empty(self):
		if len(self.graph_dict.keys()) == 0:
			return True
		return False

	#inserts vertex v into graph and returns the resulting graph
	def add_vertex(self,v):
		self.graph_dict[v] = []
		return self
	
	#deletes vertex v from graph g and returns the resulting graph
	def remove_vertex(self,v):
		if self.has_vertex(v):
			self.graph_dict.pop(v)
			#delete all edges going to v
			for vert in self.graph_dict:
				if self.has_edge(vert,v):
					remove_edge(vert,v)
		return self

	#changes the label on vertex v in graph to be l and returns the resulting graph
	def update_vertex(self,v,l):
		if self.has_vertex(v):
			for vertex in self.graph_dict:
				if vertex == v:
					vertex.label = l
		else:
			print("Error: vertex does not exist")
		return self

	#returns the label from vertex v in graph
	def get_vertex(self,v):
		for x in self.graph_dict:
			if (x == v):
				return x.label
		return None
	
	#returns true iff v is a vertex of graph
	def has_vertex(self, v):
		if v in self.graph_dict:
			return True
		return False

	#inserts and edge from vertex v1 to vertex v2 in graph and returns the resulting graph
	def add_edge(self,v1,v2,l):
		if self.has_vertex(v1) and self.has_vertex(v2):
			if not v2 in self.graph_dict[v1]:
				self.graph_dict[v1].append(Edge(v2,l))
		else:
			print("Error: at least one given vertex does not exist. Operation Aborted.")
		return self
	
	
	#deletes all the edge from vertex v1 to vertex v2 in graph g and returns the resulting graph
	def remove_edge(self,v1,v2):
		if self.has_vertex(v1) and self.has_vertex(v2):
			for edge in self.graph_dict[v1]:
				if edge.vertex == v2:
					self.graph_dict[v1].remove(edge)
		else:
			print("Error: at least one given vertex does not exist. Operation Aborted.")
		return self

	
	#changes the label on the edge from vertex v1 to vertex v2 in graph to have label l and returns the resulting graph
	def update_edge(self,v1,v2,l):
		if self.has_vertex(v1) and self.has_vertex(v2):
			for edge in self.graph_dict[v1]:
				if edge.vertex == v2:
					edge.label = l
					return self
			print("Error: edge does not exist")
			return self
		else:
			print ("Error: at least one vertex does not exist")
		return self

	#returns the label on the edge from vertex v1 to vertex v2 in graph
	def get_edge(v1,v2):
		if self.has_vertex(v1) and self.has_vertex(v2):
			for edge in self.graph_dict[v1]:
				if edge.vertex == v2:
					return edge.label
			return None
		else:
			print("Error: at least one vertex does not exist")
		return None

	
	#returns true iff there is an edge from vertex v1 to a vertex v2 in graph
	def has_edge(self,v1,v2):
		if self.has_vertex(v1) and self.has_vertex(v2):
			for edge in self.graph_dict[v1]:
				if edge.vertex == v2:
					return True
			return False
		else:
			print("Error: at least one vertex does not exist")
		return False


	#returns a sequence of all the vertices in graph
	def all_verticies(self):
		verticies = []
		for v in self.graph_dict:
			verticies.append(v)
		return verticies

	#returns a sequence of all vertices v2 such that there is an edge from vertex v1 to vertex v2 in graph
	def from_edges(self,v1):
		edges = []
		for e in self.graph_dict[v1]:
			edges.append(e.vertex)
		return edges

class Vertex():
	def __init__(self,label):
		self.label = label

class Edge(object):
	def __init__(self,vertex,label):
		self.vertex = vertex
		self.label = label
# Main:
if __name__ == "__main__":

	print("Creating graph lD")
	lD = LabeledDigraph()

	print("Checking if lD is empty")
	print(lD.is_empty())

	print("Adding vertex 'Apple' to lD")

	apple = Vertex("Apple")
	lD.add_vertex(apple)

	print("Checking if lD is empty")
	print(lD.is_empty())

	print("Adding vertex 'Orange' to lD")
	
	orange = Vertex("Orange")
	lD.add_vertex(orange)

	print("Print all verticies in lD")
	verts = lD.all_verticies()	
	for v in verts:
		print(lD.get_vertex(v))

	print("Creating graph lD2")
	lD2 = LabeledDigraph()

	print("Adding vertex 'Mango' to lD2")
	mango = Vertex("Mango")
	lD2.add_vertex(mango)
	
	print("Print each vertex label in lD2")
	verts = lD2.all_verticies()

	for v in verts:
		print(lD2.get_vertex(v))

	print("Is 'Mango' in lD2?")
	print(lD2.has_vertex(mango))

	print("Is 'Orange' in lD2?")
	print(lD2.has_vertex(orange))
	
	print("Change 'Mango' in lD2 to 'Banana'")
	lD2.update_vertex(mango, "Banana")

	print("Print each vertex label in lD2")
	verts = lD2.all_verticies()
	for v in verts:
		print(lD2.get_vertex(v))

	print("Adding edge 'Apple->Orange' to lD")

	lD.add_edge(apple, orange, "Apple->Orange")

	print("Print each edge of \"Apple\"")
	for edge in lD.from_edges(apple):
		print(edge.label)	

	print("Update edge label in lD from 'Apple->Orange' to 'New Label'")
	lD.update_edge(apple, orange, "New Label")

	print("Print each edge of \"Apple\"")
	for edge2 in lD.from_edges(apple):
		print(edge2.label)

	print("Print each vertex in lD")
	verticies = lD.all_verticies()
	for vert in verticies:
		print(lD.get_vertex(vert))

	print("Print each vertex coming this is directed to by vertex 'Apple' in lD")
	edges = lD.from_edges(apple)
	for edge3 in edges:
		print(edge3.label)

	print("Remove Apple->Orange edge")
	lD.remove_edge(apple,orange)

	print("Print each edge in Apple")
	for edge4 in lD.from_edges(apple):
		print(edge4.label)
	print("\nShould be nothing above this line")

	print("Remove vertex mango from lD2")
	lD2.remove_vertex(mango)
	
	print("Print each vertex in lD2")
	verticies2 = lD2.all_verticies()
	for vert2 in verticies2:
		print(lD2.get_vertex(vert2))


