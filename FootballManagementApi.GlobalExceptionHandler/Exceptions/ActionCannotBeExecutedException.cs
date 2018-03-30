using System;

namespace FootballManagementApi.GlobalExceptionHandler.Exceptions
{
	public class ActionCannotBeExecutedException : Exception
	{
		private string _message;
		public ActionCannotBeExecutedException(string message) => _message = message;

		public override string Message => _message;
	}
}
