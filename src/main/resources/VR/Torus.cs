using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class  Torus: MonoBehaviour
{
	// Use this for initialization
	void Start ()
	{
		Maketorus (4f,1f);
	}
	
	// Update is called once per frame
	void Update ()
	{
		
	}

	public static GameObject Maketorus(float r1, float r2) {
		GameObject torus = new GameObject ("Torus");
		Material element = Resources.Load ("White") as Material;

		Mesh torusMesh = MakeTorusMesh(r1,r2);

		MeshRenderer mr = torus.AddComponent<MeshRenderer>();
		MeshFilter mf = torus.AddComponent<MeshFilter> ();
		mf.mesh = torusMesh;
		mr.materials [0] = element;


		return torus;


	}

	static Mesh MakeTorusMesh(float r1, float r2) {

		var n = 100;
		Mesh mesh = new Mesh ();

		var vertices = new List<Vector3> ();
		var triangles = new List<int> ();
		var normals = new List<Vector3> ();

		for (int i = 0; i <= n; i++) {
			//円
			var phi = Mathf.PI * 2.0f * i / n;
			var tr = Mathf.Cos (phi) * r2;
			var y = Mathf.Sin (phi) * r2;


			for (int j = 0; j <= n; j++) {
				//トーラス
				var theta = 2.0f * Mathf.PI * j / n;
				var x = Mathf.Cos (theta) * (r1 + tr);
				var z = Mathf.Sin (theta) * (r1 + tr);

				vertices.Add (new Vector3 (x, y, z));
				// (2) 法線の計算
				normals.Add (new Vector3 (tr * Mathf.Cos (theta), y, tr * Mathf.Sin (theta)));
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				var count = (n + 1) * j + i;
				// (3) 頂点インデックスを指定
				triangles.Add (count);
				triangles.Add (count + n + 2);
				triangles.Add (count + 1);

				triangles.Add (count);
				triangles.Add (count + n + 1);
				triangles.Add (count + n + 2);
			}
		}

		mesh.vertices = vertices.ToArray ();
		mesh.triangles = triangles.ToArray ();
		mesh.normals = normals.ToArray ();

		mesh.RecalculateBounds ();

		return mesh;
	}
}

