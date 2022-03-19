using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class playerMovement : MonoBehaviour
{

	public int maxSpeed = 500;
	public int maxSprint = 750;
	public int acceleration = 10000;
	public int friction = 10000;
	

	// Start is called before the first frame update
	void Start()
    {
		//Vector2 playerVelocity = new Vector2.zero;
		Debug.Log("Hello World!");
		//var animationPlayer = $AnimationPlayer;
	}
	/*
	// Update is called once per frame
	void Update()
    {
		private inputVector = newVector2 (GetAxisRaw("Horizontal"), GetAxisRaw("Vertical"));
		inputVector = inputVector.normalized();

		if (inputVector != Vector2.zero)
		{
			if (Input.GetKeyDown("sprint") == 1)
			{
				playerVelocity += inputVector * acceleration * delta;
				playerVelocity = ClampMagnitude(playerVelocity, maxSprint);
			}
			else
			{
				playerVelocity += inputVector * acceleration * delta;
				playerVelocity = ClampMagnitude(playerVelocity, maxSpeed));
			}

		}

		else
		{
			playerVelocity = playerVelocity.MoveToward(Vector2.Zero, friction * delta);
		}

		//GD.Print(playerVelocity);


		//playerVelocity = MoveAndSlide(playerVelocity);
	
	}
	*/
}
