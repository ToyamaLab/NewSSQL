using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Rotate : MonoBehaviour {

	private float x;
	private float y;
	private float z;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
		transform.Rotate (x * 360 / 60 * Time.deltaTime, y * 360 / 60 * Time.deltaTime, z * 360 / 60 * Time.deltaTime, Space.World);

	}

	public void SetRotation(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
		
}
