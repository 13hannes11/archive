using System;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using C3.XNA;
using System.Collections.Generic;

namespace PlanetSimulation
{
	public class Planet
	{
		private long mass; public long Mass {
			get{ return mass;}
			set{ mass = value;}
		}
		private long radius; public long Radius {
			get{ return radius;}
			set{ radius = value;}
		}
		private Vector2 center; public Vector2 Center { get{return center;}}

		private List<Vector2> forces; public List<Vector2> Forces {
			get { return forces; }
			set{ forces = value;}
		} 
		private Vector2 acceleration;
		private Vector2 velocity; public Vector2 Velocity{	get{ return velocity;}}


		/// <summary>
		/// Initializes a new instance of the <see cref="PlanetSimulation.Planet"/> class.
		/// </summary>
		/// <param name='center'>
		/// Center of the planet
		/// </param>
		/// <param name='mass'>
		/// Mass of the Planet
		/// </param>
		/// <param name='radius'>
		/// Radius of the planet
		/// </param>
		public Planet (Vector2 center, long mass, long radius)
		{
			this.velocity = Vector2.Zero;

			this.mass = mass;
			this.radius = radius;
			this.center = center;
			this.forces = new List<Vector2>();
			this.acceleration = Vector2.Zero;
		}
		/// <summary>
		/// Initializes a new instance of the <see cref="PlanetSimulation.Planet"/> class.
		/// </summary>
		/// <param name='center'>
		/// Center of the planet
		/// </param>
		/// <param name='mass'>
		/// Mass of the planet
		/// </param>
		/// <param name='radius'>
		/// Radius of the planet
		/// </param>
		/// <param name='velocity'>
		/// Velocity of the planet
		/// </param>
		public Planet (Vector2 center, long mass, long radius, Vector2 velocity)
		{
			this.velocity = velocity; 

			this.mass = mass;
			this.radius = radius;
			this.center = center;
			this.forces = new List<Vector2>();
			this.acceleration = Vector2.Zero;
		}

		public void Update (GameTime gameTime)
		{
			calcAcceleration();
			calcVelocity(gameTime);
			move(gameTime);
		}
		public void Draw(SpriteBatch spriteBatch)
		{
			spriteBatch.DrawCircle(center, radius, (int)radius, Color.Red);
			/*foreach (var f in forces) {
				spriteBatch.DrawLine(center, Vector2.Add(center, Vector2.Multiply(Vector2.Normalize(f), 100)), Color.Green);
				//spriteBatch.DrawLine(center, Vector2.Add(center, f), Color.Green);
			}*/
			forces.Clear();
			//Draw Acceleration
			spriteBatch.DrawLine(center, Vector2.Add(center, Vector2.Multiply(acceleration, 1)), Color.Orange);
			//Draw Velocity
			spriteBatch.DrawLine(center, Vector2.Add(center, velocity), Color.White);
		}

		/// <summary>
		/// Moves the Planet according to it's velocity.
		/// </summary>
		/// <param name='gameTime'>
		/// GameTime element from MonoGame/XNA
		/// </param>
		private void move (GameTime gameTime)
		{
			float elapsedSeconds = (float)gameTime.ElapsedGameTime.Milliseconds / 1000;
			center = Vector2.Add(center, Vector2.Multiply(velocity, elapsedSeconds));
		}
		/// <summary>
		/// Updates the Velocity of the Planet by using the acceleration.
		/// </summary>
		/// <param name='gameTime'>
		/// GameTime element from MonoGame/XNA
		/// </param>
		private void calcVelocity (GameTime gameTime)
		{
			float elapsedSeconds = (float)gameTime.ElapsedGameTime.Milliseconds / 1000;

			velocity = Vector2.Add(velocity, Vector2.Multiply(acceleration, elapsedSeconds));
		}
		/// <summary>
		/// Calculates the acceleration out of all occouring forces which are saved in the List forces which consists of 2D Vectors.
		/// </summary>
		private void calcAcceleration()
		{
			Vector2 f = Vector2.Zero;
			foreach(Vector2 v in forces)
			{
				f = Vector2.Add(f,v);
			}

			//F = m x a <=> F/m = a
			acceleration = Vector2.Divide(f,mass);
		}
	}
}

