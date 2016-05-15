<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container modal-container">
    <!-- Modal -->
    <div class="modal fade" id="deletePayModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <span class="modal-title">Удаление платежа</span>
                </div>

                <div class="modal-body">
                    <p>Вы действительно хотите удалить платеж?</p>
                </div>

                <div class="modal-footer">
                    <form role="form" method="GET" action="PeriodicEdition" name="payDelForm" id="payDelForm">
                        <div class="form-group">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-trash"></span>Удалить</button>
                        </div>

                        <input type="hidden" name="command" value="delete_payment">
                        <input type="hidden" name="delPayId" id="delPayId" value="">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editModal" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
    <div class="modal-dialog">
        <div class="modal-content"></div>
    </div>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <span class="modal-title">Редактирование платежа</span>
            </div>

            <div class="modal-body">
                <div class="form-group">
                    <label for="editPayDate">Дата платежа</label>
                    <input type="date" class="form-control" id="editPayDate" name="editPayDate" value="">
                </div>

                <div class="form-group">
                    <label for="editDuration">Количество дней </label>
                    <input type="number" name="editDuration" min="0" max="365" class="form-control"
                           id="editDuration" value="">
                </div>

                <div class="form-group">
                    <label for="editPaySum">Сумма для оплаты</label>
                    <input type="text" class="form-control" name="editPaySum" id="editPaySum" value="">
                </div>
            </div>

            <div class="modal-footer">
                <form action="PeriodicEdition" method="POST" id="editForm" role="form">
                    <input type="hidden" name="command" value="edit_payment">
                    <input type="hidden" name="editPayId" id="editPayId" value="">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                    <button type="submit" class="btn btn-primary">Сохранить</button>
                </form>
            </div>
        </div>
    </div>
</div>


<div class="container modal-container">
    <!-- Modal -->
    <div class="modal fade" id="payModal" role="dialog">
        <div class="modal-dialog">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <span class="modal-title">Оплата</span>
                </div>

                <div class="modal-body">
                    <form role="form" method="POST" action="PeriodicEdition" name="payForm" id="payForm">
                        <input type="hidden" name="command" value="add_payment">

                        <div class="form-group">
                            <label for="subscr_id">Номер подписки</label>
                            <p>№<input type="text" class="form-control" id="subscr_id" name="subscr_id"
                                       placeholder="Введите номер"></p>
                        </div>

                        <div class="form-group">
                            <label for="duration">Количество дней </label>
                            <input type="number" name="duration" min="0" max="365" class="form-control"
                                   id="duration" placeholder="Введите количество дней" oninput="countPaySum()">
                        </div>

                        <div class="form-group">
                            <label for="paySum">Сумма для оплаты</label>
                            <input type="text" class="form-control" name="paySum" disabled id="paySum" placeholder="0">
                        </div>

                        <button type="submit" class="btn btn-primary btn-block">
                            <span class="glyphicon glyphicon-credit-card"></span>Оплатить</button>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
                </div>

            </div>
        </div>
    </div>
</div>
