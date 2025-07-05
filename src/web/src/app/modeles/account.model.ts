export interface Account {
  accountId: number
  balance: number
  accountNumber: string
  currentPage: number
  totalPage: number
  pageSize: number
  accountOperationDtos: AccountOperationDto[]
}
export interface AccountOperationDto {
  id: number
  date: string
  amount: number
  description: string
  type: string
}
