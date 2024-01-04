package io.malachai.finance.common.config;

import io.malachai.finance.domain.repository.ApiModelRepository;
import io.malachai.finance.infra.ApiModelFileLoader;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;

@Configuration
public class InitConfig {

  public InitConfig(@Value("${application.file-loader.api-model.path}") String confFile,
                    ApiModelRepository apiModelRepository) throws IOException, ParseException {
    ApiModelFileLoader loader = new ApiModelFileLoader(confFile);
    loader.load()
        .orElse(new ArrayList<>())
            .forEach(apiModelRepository::save);
  }

}
