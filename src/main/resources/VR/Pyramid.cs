using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Pyramid : MonoBehaviour {

	// Use this for initialization
	void Start () {
	}
	
	// Update is called once per frame
	void Update () {
		
	}

	public static GameObject MakePyramid(float size, float height) {
		GameObject pyramid = new GameObject ("Pyramid");
		Material element = Resources.Load ("White") as Material;

		Mesh pyramidMesh = MakePyramidMesh(size, height);

		MeshRenderer mr = pyramid.AddComponent<MeshRenderer>();
		MeshFilter mf = pyramid.AddComponent<MeshFilter> ();
		mf.mesh = pyramidMesh;
		mr.materials [0] = element;


		return pyramid;

	}

	static Mesh MakePyramidMesh(float size, float height){//size: surface of the base
		Mesh mesh = new Mesh ();
		List<Vector3> vertices = new List<Vector3> ();

		float l = 2 * Mathf.Sqrt( size / Mathf.Sqrt(3));

		Vector3 p1 = new Vector3 (0, 0, 0);
		Vector3 p2 = new Vector3 (l, 0, 0);
		Vector3 p3 = new Vector3 (l/2, 0, Mathf.Sqrt(3) * l/ 2);
		Vector3 p4 = new Vector3 (l/2, height, Mathf.Sqrt(3) * l / 4); //p1 - 0 , p2 - 1, p3 - 2, p4 - 3 arraynumber

		Vector3 center = new Vector3 (l/2, height/2, Mathf.Sqrt(3) * l / 4); //to put the origin at the center

		vertices.Add (p1 - center);
		vertices.Add (p2 - center);
		vertices.Add (p3 - center);
		vertices.Add (p4 - center);

		int[] triangles = new int[] {
			0, 1, 2,
			0, 3, 1,
//			0, 1, 3,
//			1, 2, 3
			1,3,2,
			2,3,0
		};

		mesh.vertices = vertices.ToArray ();
		mesh.triangles = triangles;

		mesh.RecalculateNormals();
		mesh.RecalculateBounds ();

		return mesh;
	}
}
