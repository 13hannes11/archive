using System;
using System.Collections.Generic;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace PlanetSimulation
{
	public class PlanetManager
	{
		public const float G = 0.02f;
		private Rectangle boundsOfUniverse;
		List<Planet> planets;
		public PlanetManager ()
		{
			int size = 5000;
			boundsOfUniverse = new Rectangle(-size,-size, 2*size, 2*size);


			Random random = new Random();

			planets = new List<Planet>();

			//planets.Add(new Planet(Vector2.Zero, 30000, 100, new Vector2(0,0)));
			//planets.Add(new Planet(new Vector2(0,300), 1000, 20, new Vector2(20,0)));
			//p.Add(new Planet(new Vector2(0,500), 20, 5, new Vector2(75,0)));
			for (int i = 0; i < 200; i++) {

				int radius = random.Next(1,40);
				planets.Add(new Planet(new Vector2(random.Next(boundsOfUniverse.X, boundsOfUniverse.Width + boundsOfUniverse.X), 
				                                   random.Next(boundsOfUniverse.Y, boundsOfUniverse.Height + boundsOfUniverse.Y))
				                       ,(radius*radius)*6, radius
				                       ,new Vector2(random.Next(-50,50),random.Next(-50,50))));
			}
		}
		public void Update (GameTime gameTime)
		{
			for (int i = 0; i < (planets.Count -1); i++) {
				for (int j = i+1; j < planets.Count; j++) {
					calcForce (planets[i], planets[j]);
					checkANDcalcCollision(planets[i], planets[j]);
				}
			}
			foreach (Planet planet in planets) {
				planet.Update(gameTime);
			}
		}
		public void Draw(SpriteBatch spriteBatch)
		{
			foreach(Planet planet in planets)
			{
				planet.Draw(spriteBatch);
			}
		}

		/// <summary>
		/// Checks if a Collision occours between the two planets occours and 
		/// if they collide it calculates the Impact of the collision and the resulting force.
		/// </summary>
		/// <param name='p'>
		/// first planet
		/// </param>
		/// <param name='q'>
		/// second plane
		/// </param>
		private void checkANDcalcCollision (Planet p, Planet q)
		{
			var dx = p.Center.X - q.Center.X;
			var dy = p.Center.Y - q.Center.Y;
			var dist = p.Radius + q.Radius;
 
			if (dx * dx + dy * dy <= dist * dist) {
				//The planets Crash...

				Vector2 v = Vector2.Add(Vector2.Multiply(p.Velocity,((float)p.Mass/(p.Mass + q.Mass))), Vector2.Multiply(q.Velocity,((float)q.Mass/(q.Mass + p.Mass))));
				Vector2 center = (p.Mass > q.Mass)?p.Center:q.Center;
				long r =  (long)Math.Sqrt((p.Radius*p.Radius + q.Radius* q.Radius));

				Planet _tmp = new Planet(center, p.Mass + q.Mass, r, v);
				planets.Add(_tmp);
				planets.Remove(p);
				planets.Remove(q);
				//Insert What happens when the planets crash here
			}
		}
		/// <summary>
/// Calculates the force of gravity which occours between the two planets
///	and adds it to the List of Forces of each Planet 
/// </summary>
/// <param name='p'>
/// first planet.
/// </param>
/// <param name='q'>
/// second planet.
/// </param>		
		private void calcForce(Planet p, Planet q)
		{
			//http://goo.gl/Yt6vdj Newtons law of Gravity
			Vector2 direction = Vector2.Add(-p.Center,q.Center);
			float F = G* ((p.Mass * q.Mass)/(Vector2.Distance(p.Center,q.Center)));

			Vector2 Force = Vector2.Multiply(Vector2.Normalize(direction), F);
			p.Forces.Add(Force);
			q.Forces.Add(Vector2.Multiply(Force, -1f));
		}

	}
}

