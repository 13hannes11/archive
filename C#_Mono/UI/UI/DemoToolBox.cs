using System;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace UI
{
	public class DemoToolBox : ToolWindow
	{
		Button submit;
		public DemoToolBox (Vector2 pos, Vector2 size) : base(pos,size)
		{
			submit = new Button (offset, new Rectangle (10, 10, 100, 20), Color.Gray, 2, Color.Black);
			uiElements.Add(submit);
		}
	}
}

