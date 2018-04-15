using Newtonsoft.Json;

namespace FootballManagementApi.Requests.PostCommentRequests
{
	public class AddRequest
	{
		[JsonProperty("comment")]
		public string Comment { get; set; }
	}

	public class DeleteRequest
	{
		[JsonProperty("id")]
		public int Id { get; set; }
	}
}
