# Real-Time Developer Practice Workflow

Use this project as if you joined an existing company team.

### Phase 1 - Ticket pickup
- Read Jira title, description, acceptance criteria.
- Identify business impact.
- Decide whether it is bug/change/enhancement.
- Do not code yet.

### Phase 2 - Code investigation
Trace:
Controller -> Service -> Repository -> Entity/DB

Search by endpoint, field name, exception text, or business term.
Read existing tests before changing implementation.

### Phase 3 - Implementation
- Change only impacted code.
- Follow existing naming/style.
- Avoid unrelated refactoring.
- Add validation at the correct layer.
- Keep transaction behavior safe.

### Phase 4 - Verification
- Run unit tests.
- Test positive case.
- Test negative cases.
- Test regression cases.
- Check that database state is correct after failure.

### Phase 5 - Jira update
Example:
"Implemented ABC123 in DistributorOrderService. Added quality-status validation so only APPROVED batches can be ordered. Added unit coverage for PENDING and REJECTED batches and verified existing stock validation. Maven test suite is passing."

### Phase 6 - Interview explanation
Be ready to explain:
- Why service layer?
- Why before stock deduction?
- What happens inside the transaction?
- What happens if validation fails?
- Which classes were changed?
- How did you test it?
