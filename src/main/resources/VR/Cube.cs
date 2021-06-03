using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Cube : MonoBehaviour {

	public GameObject cube1;


	// Use this for initialization
	void Start () {
		
		for (int j = 0; j < 10; j++) {
			for (int i = 0; i < 10; i++) {
				GameObject stick = (GameObject)Instantiate (cube1, new Vector3 (i * 10, 0, j * 10), Quaternion.identity);
				stick.transform.localScale += Vector3.up * i; 
			}

		}
		 

	}
	
	// Update is called once per frame
	void Update () {

	}
}
