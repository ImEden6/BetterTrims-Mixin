# Changelog

## 1.0.1 (2026-03-24)

### Fixed
- Resolved a startup crash caused by an `InvalidMixinException` in `GuardianEntityMixin`.
- Fixed an issue where the `@Shadow` method `getType()` could not be found in the target class.
- Replaced the shadowed call with a safer `instanceof ElderGuardianEntity` check.

