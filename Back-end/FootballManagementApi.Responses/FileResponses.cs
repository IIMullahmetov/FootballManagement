using System;
using Newtonsoft.Json;

namespace FootballManagementApi.FileResponses
{
    public class UploadResponse
    {
        [JsonProperty("guid")]
        public Guid Guid { get; set; }
    }
}
