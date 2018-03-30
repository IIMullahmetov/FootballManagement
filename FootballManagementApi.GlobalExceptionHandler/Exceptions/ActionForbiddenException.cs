using System;
using System.Net;

namespace FootballManagementApi.GlobalExceptionHandler.Exceptions
{
	public class ActionForbiddenException : Exception
	{
		private string _message;

		public ActionForbiddenException() { }

		public ActionForbiddenException(string message) => _message = message;

		public override string Message => _message;
	}
}
