package org.highmed.fhir.search.parameters.basic;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

import org.highmed.fhir.search.parameters.basic.SortParameter.SortDirection;
import org.hl7.fhir.r4.model.DomainResource;

public abstract class AbstractCanonicalUrlParameter<R extends DomainResource> extends AbstractSearchParameter<R>
{
	public static enum UriSearchType
	{
		PRECISE(""), BELOW(":below"); // TODO, ABOVE(":above");

		public final String sufix;

		private UriSearchType(String sufix)
		{
			this.sufix = sufix;
		}
	}

	protected static class CanonicalUrlAndSearchType
	{
		public final String url;
		public final String version;
		public final UriSearchType type;

		private CanonicalUrlAndSearchType(String url, String version, UriSearchType type)
		{
			this.url = url;
			this.version = version;
			this.type = type;
		}
	}

	protected CanonicalUrlAndSearchType valueAndType;

	public AbstractCanonicalUrlParameter(String parameterName)
	{
		super(parameterName);
	}

	public AbstractCanonicalUrlParameter(String parameterName, SortDirection sortDirection, String url, String version,
			UriSearchType type)
	{
		super(parameterName, sortDirection);

		if (url != null && type != null)
			valueAndType = new CanonicalUrlAndSearchType(url, version, type);
	}

	@Override
	protected final void configureSearchParameter(MultivaluedMap<String, String> queryParameters)
	{
		String precise = queryParameters.getFirst(parameterName);
		if (precise != null && !precise.isBlank())
			valueAndType = toValueAndType(precise, UriSearchType.PRECISE);

		String below = queryParameters.getFirst(parameterName + UriSearchType.BELOW.sufix);
		if (below != null && !below.isBlank())
			valueAndType = toValueAndType(below, UriSearchType.BELOW);

		// TODO
		// String above = queryParameters.getFirst(parameterName + UriSearchType.ABOVE.sufix);
		// if (above != null && !above.isBlank())
		// {
		// valueAndType = new UriValueAndSearchType(above, UriSearchType.ABOVE);
		// return;
		// }
	}

	protected static CanonicalUrlAndSearchType toValueAndType(String parameter, UriSearchType type)
	{
		if (parameter != null && !parameter.isBlank())
		{
			String[] split = parameter.split("[|]");
			if (split.length == 1)
				return new CanonicalUrlAndSearchType(split[0], null, type);
			else if (split.length == 2)
				return new CanonicalUrlAndSearchType(split[0], split[1], type);
		}

		return null;
	}

	@Override
	public boolean isDefined()
	{
		return valueAndType != null;
	}

	protected boolean hasVersion()
	{
		return isDefined() && valueAndType.version != null;
	}

	@Override
	public void modifyBundleUri(UriBuilder bundleUri)
	{
		bundleUri.replaceQueryParam(parameterName + valueAndType.type.sufix,
				valueAndType.url + (hasVersion() ? ("|" + valueAndType.version) : ""));
	}
}
