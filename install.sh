curl -s https://get.sdkman.io | bash
source ~/.bashrc
sdk install kotlin

if ! $(grep -q "Plugin 'udalov/kotlin-vim'" ~/.vimrc); then
    cp ~/.vimrc ~/.vimrc-bkup
    sed -i "/call vundle#begin()/a Plugin 'udalov/kotlin-vim'" ~/.vimrc
fi

if [ ! -d ~/.vim ]; then
    mkdir ~/.vim
fi

FT=~/.vim/filetype.vim
if [ ! -f $FT ]; then
    touch $FT
    echo 'if exists("did_load_filetypes")' > $FT
    echo "    finish" >> $FT
    echo "endif" >> $FT
    echo "augroup filetypedetect" >> $FT
    echo "    au! BufNewFile,BufRead *.kt setf kotlin" >> $FT
    echo "augroup END" >> $FT
fi

