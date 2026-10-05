const $=(s,c=document)=>c.querySelector(s);const $$=(s,c=document)=>[...c.querySelectorAll(s)];
function showToast(message){
  const t=$('#toast');if(!t)return;t.textContent=message;t.classList.add('show');setTimeout(()=>t.classList.remove
  ('show'),2200)
}
function escapeHtml(v){
  return v.replace(/[&<>'"]/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;',"'":'&#39;','"':'&quot;'}[c])
)}

document.addEventListener('DOMContentLoaded',()=>{
  const login=$('#loginForm'); if(login){login.addEventListener('submit',e=>{e.preventDefault();if(!login.checkValidity()){
    login.reportValidity();return}localStorage.setItem('redsocial_usuario','Jhovan D.');location.href='inicio.html'}
  )}
  const reg=$('#registerForm'); if(reg){reg.addEventListener('submit',e=>{
    e.preventDefault();if(!reg.checkValidity()){reg.reportValidity();return}
    const p=$('#regPassword').value,c=$('#confirmPassword').value;if(p!==c){
      showToast('Las contraseñas no coinciden');return}
      localStorage.setItem('redsocial_usuario',$('#nombre').value.trim()||'Usuario');showToast('Cuenta creada correctamente');
      setTimeout(()=>location.href='inicio.html',700
    )}
  )}

  $$('.like-btn').forEach(b=>b.addEventListener('click',()=>{
    b.classList.toggle('liked');b.textContent=b.classList.contains('liked')?'♥ Me gusta':'♡ Me gusta'}));
  const postForm=$('#postForm'),postText=$('#postText'),posts=$('#posts');

  if(postForm&&postText&&posts){postForm.addEventListener('submit',e=>{
    e.preventDefault();const txt=postText.value.trim();if(!txt){showToast('Escribe algo antes de publicar');return}
    const a=document.createElement('article');
    a.className='card post';a.innerHTML=`<div class="post-head"><div class="avatar-sm">JD</div><div>
    <strong>Jhovan D.</strong><div class="muted">Ahora mismo</div></div></div><p>
    ${escapeHtml(txt)}</p><div class="post-footer">
    <button class="action-btn like-btn">♡ Me gusta</button>
    <button class="action-btn comment-btn">💬 Comentar</button></div>`;
    a.querySelector('.like-btn').addEventListener('click',ev=>{
      const b=ev.currentTarget;b.classList.toggle('liked');
      b.textContent=b.classList.contains('liked')?'♥ Me gusta':'♡ Me gusta'});
      a.querySelector('.comment-btn').addEventListener('click',()=>showToast('Comentarios: demostración frontend'));
      posts.prepend(a);postText.value='';showToast('Publicación creada')
    }
    )}

  $$('.comment-btn').forEach(b=>b.addEventListener('click',()=>showToast('Comentarios: demostración frontend')));
  const mf=$('#messageForm'),mi=$('#messageInput'),ml=$('#messages');if(mf&&mi&&ml){mf.addEventListener('submit',e=>{
    e.preventDefault();const txt=mi.value.trim();if(!txt)return;
    const d=document.createElement('div');d.className='bubble sent';
    d.textContent=txt;ml.appendChild(d);mi.value='';ml.scrollTop=ml.scrollHeight}
  )}

  const edit=$('#editProfileBtn'); if(edit){edit.addEventListener('click',()=>showToast('Edición de perfil: demostración frontend'))}
  const mark=$('#markReadBtn');if(mark){mark.addEventListener('click',()=>{$$('.notification.unread').forEach(n=>n.classList.remove('unread'));
    const b=$('#notifBadge');if(b)b.remove();showToast('Notificaciones marcadas como leídas')}
  )}
  
  $$('.conversation-list li').forEach(li=>li.addEventListener('click',()=>showToast('Conversación seleccionada: '+li.dataset.name)));
});
