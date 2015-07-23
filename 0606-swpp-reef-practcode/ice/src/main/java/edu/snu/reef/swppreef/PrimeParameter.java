package edu.snu.reef.swppreef;

import org.apache.reef.tang.annotations.Name;
import org.apache.reef.tang.annotations.NamedParameter;

@NamedParameter(doc = "Integer parameter for PrimeTask")
public final class PrimeParameter implements Name<Integer> {
}
