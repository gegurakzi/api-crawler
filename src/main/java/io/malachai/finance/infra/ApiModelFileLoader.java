package io.malachai.finance.infra;

import io.malachai.finance.domain.ApiModel;
import io.malachai.finance.domain.repository.ApiModelRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ApiModelFileLoader {

  private final String confFile;

  public ApiModelFileLoader(String confFile) {
    this.confFile = confFile;
  }

  public Optional<List<ApiModel>> load() throws IOException, ParseException {
    JSONParser parser = new JSONParser();
    return Optional.of(((List) parser.parse(new FileReader(confFile))).stream().map(o -> {
      JSONObject json = (JSONObject) o;
      return new ApiModel(
          null,
          (String) json.get("name"),
          (String) json.get("group"),
          (String) json.get("url"),
          (String) json.get("header")
      );
    }).toList());
  }

}
