package org.highmed.bpe.spring.config;

import org.highmed.bpe.message.SendPing;
import org.highmed.bpe.message.SendPong;
import org.highmed.bpe.plugin.PingPlugin;
import org.highmed.bpe.service.LogPing;
import org.highmed.bpe.service.LogPong;
import org.highmed.bpe.service.SelectTarget;
import org.highmed.bpe.service.SelectTargets;
import org.highmed.fhir.client.ClientProvider;
import org.highmed.fhir.organization.OrganizationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PingConfig
{
	@Autowired
	private ClientProvider clientProvider;

	@Autowired
	private OrganizationProvider organizationProvider;

	@Bean
	public PingPlugin pingPlugin()
	{
		return new PingPlugin();
	}

	@Bean
	public SendPing sendPing()
	{
		return new SendPing(organizationProvider, clientProvider);
	}

	@Bean
	public SendPong sendPong()
	{
		return new SendPong(organizationProvider, clientProvider);
	}

	@Bean
	public LogPing logPing()
	{
		return new LogPing();
	}

	@Bean
	public LogPong logPong()
	{
		return new LogPong();
	}

	@Bean
	public SelectTargets selectTargets()
	{
		return new SelectTargets(organizationProvider);
	}

	@Bean
	public SelectTarget selectTarget()
	{
		return new SelectTarget(organizationProvider);
	}
}
