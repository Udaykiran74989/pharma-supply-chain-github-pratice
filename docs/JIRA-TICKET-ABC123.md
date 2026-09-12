# Jira Ticket ABC123 — Practice Assignment

**Title:** Prevent distributors from ordering unapproved medicine batches

**Type:** Bug / Business Rule Change  
**Priority:** High  
**Module:** Distributor Order  
**Environment:** QA

## Business problem
A distributor can currently place an order against a medicine batch whose quality inspection is still `PENDING`.
This can cause unapproved stock to enter the downstream supply chain.

## Acceptance criteria
1. A distributor order must be created only when the selected batch has `APPROVED` quality status.
2. If the batch status is `PENDING` or `REJECTED`, the API must reject the request.
3. Existing insufficient-stock validation must continue to work.
4. Stock must NOT be reduced when the order is rejected.
5. Existing successful-order behavior must remain unchanged.
6. Add/update unit tests for approved, pending and rejected cases.

## Suggested API
POST `/api/distributor-orders`

Request:
```json
{
  "distributorCode": "DIST-01",
  "batchNumber": "BATCH-PARA-001",
  "quantity": 10
}
```

Expected for PENDING:
HTTP 400

Expected message:
`Batch is not approved for ordering`

## Developer expectation
Do not redesign the module. Make the smallest safe change in the service layer, add tests, run the full test suite, and report what changed.

## Your first job
Pretend this ticket has just been assigned to you.
Before editing code, inspect:
- DistributorOrderController
- DistributorOrderService
- MedicineBatch
- QualityStatus
- MedicineBatchRepository
- existing tests

Then explain the current flow to yourself.
