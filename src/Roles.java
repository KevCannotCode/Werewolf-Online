import javax.management.relation.RoleStatus;

public enum Roles {
	VILLAGER,WEREWOLF;
	enum roleType {GOOD,EVIL,NEUTRAL};
	
	public static boolean isGood(String role) {
		return getRoleType(role).equals(roleType.GOOD.toString());
	}
	
	public static boolean isEvil(String role) {
		return getRoleType(role).equals(roleType.EVIL.toString());
	}

	public static boolean isNeutral(String role) {
		return getRoleType(role).equals(roleType.NEUTRAL.toString());
	}
	
	private static String getRoleType(String role) {
		switch (role) {
		case "VILLAGER" : 
			return Roles.roleType.GOOD.toString();
		case "WEREWOLF" : 
			return Roles.roleType.EVIL.toString();
		default:
			throw new IllegalArgumentException("Unexpected value: " + role);
		}
	}
}
