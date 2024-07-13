package br.com.jovemtech.productordermanager.config;

import org.hibernate.collection.spi.PersistentSet;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
public class ModelMapperBeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new PersistentSetToSetConverter());
        return modelMapper;
    }

    private static class PersistentSetToSetConverter implements Converter<PersistentSet<?>, Set<?>> {
        @Override
        public Set<?> convert(MappingContext<PersistentSet<?>, Set<?>> context) {
            return context.getSource() != null ? new LinkedHashSet<>(context.getSource()) : null;
        }
    }
}

