git init
git add .
git status
git commit -m $1
git remote add origin https://github.uconn.edu/kbn14002/GTEFP.git
git remote -v
git push origin master -f
